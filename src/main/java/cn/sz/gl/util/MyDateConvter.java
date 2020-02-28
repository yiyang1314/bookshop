package cn.sz.gl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyDateConvter implements Converter<String, Date> {

	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(source);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
