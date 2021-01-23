package io.pluto.document.helper;

public class Line {

    private String content;
    private int limit;
    private final Document parent;

    public static Line of(String content, int charLimit) {
        return new Line(charLimit, content, new Document("Unnamed Doc"));
    }

    protected Line(int length, Document parent) {
        this(length, "", parent);
    }
    protected Line(int length, String startingContent, Document parent) {
        limit = length;
        content = startingContent;
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public Document getParent() {
        return parent;
    }

    public void insertCharacterAtIndex(int index, char character) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (i == limit - 1) {
                builder.append(character);
                getNextLine().insertCharacterAtIndex(0, c);
            } else if (i == limit) {
                getNextLine().insertCharacterAtIndex(0, c);
                getNextLine().insertCharacterAtIndex(0, character);
            } else {
                if (i == index) {
                    builder.append(character).append(c);
                } else {
                    builder.append(c);
                }
            }
        }
        content = builder.toString();
    }

    private Line getNextLine() {
        int index = parent.getIndexOfLine(this);
        if (index == parent.getLineCount()) {
            return parent.addLine();
        } else {
            return parent.getLines()[index + 1];
        }
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
