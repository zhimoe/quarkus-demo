package moe.zhi.model;


import lombok.Data;

@Data
public class CountdownDay {
    String id;
    String date;
    boolean remind;
    String title;
    String detail;
}
