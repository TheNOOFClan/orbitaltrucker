package com.github.thenoofclan.orbitaltrucker.util;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class JsonReader
{
	private Reader stream;
	
	public JsonReader(String filename) throws IOException
	{
		stream = Gdx.files.internal(filename).reader();
	}
	
	public JsonObject parse() throws IOException
	{
		JsonContainer root = new JsonContainer(null);
		JsonContainer current = root;
		int depth = 0;
		byte containerType = 0; // root
		
		int nextChar;
		while (true)
		{
			nextChar = skipWhitespace();
			
			if (containerType == 2) // current container is a map
			{
				if (nextChar != '"')
					throw new IllegalArgumentException(
							"Incorrect JSON format: string key expected, but no string opener found");
				
				String key = parseString();
				((JsonMap) current).primeKey(key);
				nextChar = skipWhitespace();
				
				if (nextChar != ':')
					throw new IllegalArgumentException(
							"Incorrect JSON format: key separator (':') expected, but not found");
				
				nextChar = skipWhitespace();
			}
			
			if (nextChar == '[' || nextChar == '{') // container opener
			{
				JsonContainer nextObj;
				if (nextChar == '[')
					nextObj = new JsonArray(current);
				else
					nextObj = new JsonMap(current);
				depth = depth + 1;
				containerType = nextObj.containerType();
				current.add(nextObj);
				current = nextObj;
			} else // primitive opener
			{
				JsonObject nextObj;
				if (nextChar == '"') // string opener
				{
					String data = parseString();
					nextObj = new JsonString(current, data);
					nextChar = skipWhitespace();
				} else if ((nextChar >= '0' && nextChar <= '9') || nextChar == '-') // beginning of number
				{
					Number[] numbers = parseDouble(nextChar);
					nextObj = new JsonNumber(current, (double) numbers[0]);
					nextChar = (int) numbers[1];
				} else if (nextChar == 't' || nextChar == 'f' || nextChar == 'n') // beginning of boolean
				{
					Boolean data = parseBoolean(nextChar);
					nextObj = new JsonBoolean(current, data);
					nextChar = skipWhitespace();
				} else
				{
					throw new IllegalArgumentException(
							"Incorrect JSON format: expected primitive initializer, but none found");
				}
				current.add(nextObj);
				while (nextChar == '}' || nextChar == ']') // close containers
				{
					if ((nextChar == '}' && containerType != 2) || (nextChar == ']' && containerType != 1))
						throw new IllegalArgumentException("Incorrect JSON format: incorrect container closure");
					current = current.parent;
					depth = depth - 1;
					containerType = current.containerType();
					nextChar = skipWhitespace();
				}
				
				if (nextChar == -1) // eof
					break;
				if (nextChar != ',')
					throw new IllegalArgumentException("Incorrect JSON format: element separator expected");
			}
		}
		if (depth != 0)
			throw new IllegalArgumentException("Incorrect JSON format: mismatch of depth");
		stream.close();
		return current;
	}
	
	private int skipWhitespace() throws IOException
	{
		int c = ' ';
		while (c == ' ' || c == '\n' || c == '\t')
			c = stream.read();
		return c;
	}
	
	private String parseString() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		int next = stream.read();
		while (next != '"')
		{
			sb.append((char) next);
			next = stream.read();
		}
		return sb.toString();
	}
	
	private Number[] parseDouble(int c) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		while ((c >= '0' && c <= '9') || c == '-' || c == '.')
		{
			sb.append((char) c);
			c = stream.read();
		}
		if (c == ' ' || c == '\n' || c == '\t')
			c = skipWhitespace();
		Number[] ans = { Double.parseDouble(sb.toString()), c };
		return ans;
	}
	
	private Boolean parseBoolean(int c) throws IOException
	{
		char[] buf = new char[4];
		buf[0] = (char) c;
		stream.read(buf, 1, 3);
		String s = new String(buf);
		if (s.equals("true"))
			return true;
		if (s.equals("false"))
			return false;
		if (s.equals("null"))
			return null;
		throw new IllegalArgumentException(
				"Incorrect JSON format: keyword (true/false/null) expected, but not matched");
	}
	
	public abstract class JsonObject
	{
		public JsonContainer parent;
		
		public JsonObject(JsonContainer parent)
		{
			this.parent = parent;
		}
		
		@Override
		public String toString()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not a string");
		}
		
		public double toDouble()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not a number (double)");
		}
		
		public int toInt()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not a number (int)");
		}
		
		public Boolean toBool()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not a boolean");
		}
		
		public JsonObject[] toArray()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not an array");
		}
		
		public Map<String, JsonObject> toMap()
		{
			throw new UnsupportedOperationException(
					"Conversion error: the object you are trying to convert is not a map");
		}
	}
	
	public class JsonNumber extends JsonObject
	{
		private double data;
		
		public JsonNumber(JsonContainer parent, double data)
		{
			super(parent);
			this.data = data;
		}
		
		@Override
		public double toDouble()
		{
			return data;
		}
		
		@Override
		public int toInt()
		{
			return (int) data;
		}
	}
	
	public class JsonString extends JsonObject
	{
		private String data;
		
		public JsonString(JsonContainer parent, String data)
		{
			super(parent);
			this.data = data;
		}
		
		@Override
		public String toString()
		{
			return data;
		}
	}
	
	public class JsonBoolean extends JsonObject
	{
		private Boolean data;
		
		public JsonBoolean(JsonContainer parent, Boolean data)
		{
			super(parent);
			this.data = data;
		}
		
		public Boolean toBoolean()
		{
			return data;
		}
	}
	
	public class JsonContainer extends JsonObject
	{
		private JsonObject contained;
		
		public JsonContainer(JsonContainer parent)
		{
			super(parent);
		}
		
		public void add(JsonObject o)
		{
			contained = o;
		}
		
		public byte containerType()
		{
			return 0;
		}
		
		@Override
		public String toString()
		{
			return contained.toString();
		}
		
		@Override
		public int toInt()
		{
			return contained.toInt();
		}
		
		@Override
		public double toDouble()
		{
			return contained.toDouble();
		}
		
		@Override
		public Boolean toBool()
		{
			return contained.toBool();
		}
		
		@Override
		public JsonObject[] toArray()
		{
			return contained.toArray();
		}
		
		@Override
		public Map<String, JsonObject> toMap()
		{
			return contained.toMap();
		}
	}
	
	public class JsonArray extends JsonContainer
	{
		private List<JsonObject> data;
		
		public JsonArray(JsonContainer parent)
		{
			super(parent);
			data = new LinkedList<JsonObject>();
		}
		
		@Override
		public void add(JsonObject obj)
		{
			data.add(obj);
		}
		
		@Override
		public byte containerType()
		{
			return 1;
		}
		
		@Override
		public JsonObject[] toArray()
		{
			return (JsonObject[]) data.toArray();
		}
	}
	
	public class JsonMap extends JsonContainer
	{
		private String nextKey;
		private List<Object[]> data;
		
		public JsonMap(JsonContainer parent)
		{
			super(parent);
			data = new LinkedList<Object[]>();
		}
		
		public void primeKey(String key)
		{
			nextKey = key;
		}
		
		@Override
		public void add(JsonObject obj)
		{
			data.add(new Object[] { nextKey, obj });
		}
		
		@Override
		public byte containerType()
		{
			return 2;
		}
		
		@Override
		public Map<String, JsonObject> toMap()
		{
			Map<String, JsonObject> ans = new HashMap<String, JsonObject>();
			for (Object[] tmp : data)
			{
				ans.put((String) tmp[0], (JsonObject) tmp[1]);
			}
			return ans;
		}
	}
}