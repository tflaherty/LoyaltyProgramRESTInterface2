package hello;

import jpamappings.CurveEntryPK;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Tom on 8/14/2016.
 */
@Component
public class CurveEntryPK2String implements Converter<CurveEntryPK, String>
{
    @Override
    public String convert(CurveEntryPK s)
    {
        return "foobar";
    }
}