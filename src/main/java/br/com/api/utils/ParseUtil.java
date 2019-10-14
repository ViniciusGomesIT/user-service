package br.com.api.utils;

import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.api.model.MessagesModel;

@Component
public class ParseUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ParseUtil.class);
	private ModelMapper mapper = new ModelMapper();
	
	@Inject
	private MessagesModel message;

	@SuppressWarnings("unchecked")
	public <T> T objectParser(Object sourceObjectToBeParsed, Class<T> targetClassToParse) {
		Optional<Object> clazz = Optional.empty();
		T clazz2 = null;

		try {
			clazz = Optional.ofNullable( mapper.map(sourceObjectToBeParsed, targetClassToParse.newInstance().getClass()) );
			
			if ( clazz.isPresent() ) {
				clazz2 = (T) clazz.get();
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			log.error( message.getParseUtilsError(), e.getCause(), e.getMessage() );
			log.error( Arrays.toString(e.getStackTrace()) );
		}

		return clazz2;
	}
}
