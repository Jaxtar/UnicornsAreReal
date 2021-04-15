package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.sql.Date;
import java.time.LocalDate;

public class SqlDateToLocalDateConverter
        implements Converter<LocalDate, Date> {
    @Override
    public Result<Date> convertToModel(LocalDate value,
                                       ValueContext context) {
        if (value == null) {
            return Result.ok(null);
        }
        return Result.ok( Date.valueOf( value) );
    }
    @Override
    public LocalDate convertToPresentation(Date value,
                                           ValueContext context) {
        return value.toLocalDate();
    }

    @Override
    public <T> Converter<LocalDate, T> chain(Converter<Date, T> other) {
        return null;
    }
}