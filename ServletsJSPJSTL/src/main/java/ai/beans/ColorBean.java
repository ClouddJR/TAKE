package ai.beans;

public class ColorBean {

    public ColorBean() {
    }

    private String backgroundColor;
    private String foregroundColor;
    private Boolean edges;

    public Boolean getEdges() {
        return edges;
    }

    public void setEdges(Boolean edges) {
        this.edges = edges;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
