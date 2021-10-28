package com.springstream.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCount {
    private String word;

    private long count;

    private Date start;

    private Date end;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WordCount{");
        sb.append("word='").append(word).append('\'');
        sb.append(", count=").append(count);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }
}
