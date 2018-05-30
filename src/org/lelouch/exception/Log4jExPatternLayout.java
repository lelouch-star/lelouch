package org.lelouch.exception;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jExPatternLayout extends PatternLayout {
    public Log4jExPatternLayout(String pattern) {
        super(pattern);
    }

    public Log4jExPatternLayout() {
        super();
    }

    /**
     * 重写createPatternParser方法，返回PatternParser的子类
     */
    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new Log4jExPatternParser(pattern);
    }

    public class Log4jExPatternParser extends PatternParser {

        public Log4jExPatternParser(String pattern) {
            super(pattern);
        }

        /**
         * 重写finalizeConverter，对特定的占位符进行处理，T表示线程ID占位符
         */
        @Override
        protected void finalizeConverter(char c) {
            if (c == 'T'){
                this.addConverter(new ExPatternConverter(this.formattingInfo));
            }
            else {
                super.finalizeConverter(c);
            }
        }

        private class ExPatternConverter extends PatternConverter {

            public ExPatternConverter(FormattingInfo fi) {
                super(fi);
            }

            /**
             * 当需要显示线程ID的时候，返回当前调用线程的ID
             */
            @Override
            protected String convert(LoggingEvent event) {
                return String.valueOf("-ThreadInfo[ "+Thread.currentThread().getId()+" | "+Thread.currentThread().getName()+" ]");
            }

        }

    }

}

