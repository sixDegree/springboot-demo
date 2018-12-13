package com.cj.demo.converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties>{

	public PropertiesHttpMessageConverter() {
        // 设置支持的 MediaType
        super(new MediaType("text", "properties"));
    }
	
	@Override
	public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return readInternal(null, inputMessage);
	}

	@Override
	protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
	
        MediaType mediaType = outputMessage.getHeaders().getContentType();
        Charset charset = mediaType.getCharset();
        charset = (charset == null) ? Charset.forName("UTF-8") : charset;
        Writer writer = new OutputStreamWriter(outputMessage.getBody(), charset);
        properties.store(writer,"From PropertiesHttpMessageConverter");
	}

	@Override
	protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
        MediaType mediaType = inputMessage.getHeaders().getContentType();
        Charset charset = mediaType.getCharset();
        charset = (charset == null) ? Charset.forName("UTF-8") : charset;
        InputStreamReader reader = new InputStreamReader(inputMessage.getBody(),charset);
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
	}

}
