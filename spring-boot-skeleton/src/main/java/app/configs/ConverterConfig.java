package app.configs;

import app.enums.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConverterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OperationTypeConverter());
    }

    class OperationTypeConverter implements Converter<String, Status> {

        public Status convert(String source) {
            try {
                return Status.parse(Integer.parseInt(source)).orElse(null);
            } catch (Exception e) {
                return null;
            }
        }


    }

}