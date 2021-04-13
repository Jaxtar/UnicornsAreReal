package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.sql.Time;
import java.time.LocalTime;

public class SqlTimeToLocalTimeConverter
        implements Converter<LocalTime, Time>

{
    @Override
    public Result<Time> convertToModel(LocalTime value,
                                       ValueContext context) {
        if (value == null) {
            return Result.ok(null);
        }
        return Result.ok( Time.valueOf( value) );
    }
    @Override
    public LocalTime convertToPresentation(Time value,
                                           ValueContext context) {
        return value.toLocalTime();
    }
}