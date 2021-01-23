package io.pluto.document.helper;

import java.util.Stack;

public class Document {

    private final Stack<Line> lines;
    private int lineLength;
    private String name;

    public Document(String name) {
        this(name, 40, 10);
    }

    public Document(String name, int lineLength) {
        this(name, lineLength, 10);
    }

    public Document(String name, int lineLength, int lineCount) {
        lines = new Stack<>();
        lines.setSize(lineCount);
        this.lineLength = lineLength;
        this.name = name;
        for (int i = 0; i < lineCount; i++) {
            Line line = new Line(lineLength, this);
            lines.set(i, line);
        }
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLineCount() {
        return lines.size();
    }

    public Line[] getLines() {
        int x = lines.size();
        Line[] lines = new Line[x];
        for (int i = 0; i < x; i++) {
            lines[i] = this.lines.elementAt(i);
        }
        return lines;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Line l : lines) {
            String line = l.getContent() + '\n';
            builder.append(line);
        }
        return builder.toString();
    }

    public Line getLineFromIndex(int index) {
        return lines.elementAt(index);
    }

    public Line addLine(String content) {
        Line line = new Line(lineLength, content, this);
        lines.add(line);
        return line;
    }

    public Line addLine(String content, int index) {
        Line line = new Line(lineLength, content, this);
        lines.add(index, line);
        return line;
    }

    public Line addLine(int index) {
        Line line = new Line(lineLength, this);
        lines.add(index, line);
        return line;
    }

    public Line addLine() {
        Line line = new Line(lineLength, this);
        lines.add(line);
        return line;
    }

    public int getIndexOfLine(Line l) {
        for (int i = 0; i < lines.size(); i++) {
            Line li = lines.elementAt(i);
            if (l.equals(li)) {
                return i;
            }
        }
        return 0;
    }

}
