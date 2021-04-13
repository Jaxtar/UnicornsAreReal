package school.newton.sysjs2.grupp3.UAR.UI.converter;

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
}
//rad 18:   return Result.ok( Date.valueOf( value) );
//lägg till
//rad 23:   if (value == null){
//rad 24:   return LocalDate.now();
