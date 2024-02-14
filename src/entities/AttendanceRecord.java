package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRecord {
    private int id;
    private int userId;
    private int courseId;
    private LocalDate date;
    private boolean present;
}
