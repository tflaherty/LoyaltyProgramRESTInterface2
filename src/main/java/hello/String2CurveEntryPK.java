package hello;


import jpamappings.CurveEntryPK;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by Tom on 8/12/2016.
 */
@Component
public class String2CurveEntryPK implements Converter<String, CurveEntryPK>
{
    @Override
    public CurveEntryPK convert(String s)
    {
        String[] result = s.split(Pattern.quote("||"));
        return new CurveEntryPK(Integer.parseInt(result[0]), Integer.parseInt(result[1]));
    }
}