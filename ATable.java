import java.util.ArrayList;
import java.util.List;

public class ATable {
    private  String name;
    private List<String> column = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private List<String> pktable = new ArrayList<>();
    private List<String> fkName = new ArrayList<>();
    private List<String> pkName = new ArrayList<>();

    public ATable(){

    }



    public ATable(String name, List<String> column, List<String> type, List<String> pktable, List<String> fkName) {
        this.name = name;
        this.column = column;
        this.type = type;
        this.pktable = pktable;
        this.fkName =fkName;
    }
    public ATable(String name, List<String> column, List<String> type, List<String> pktable) {
        this.name = name;
        this.column = column;
        this.type = type;
        this.pktable = pktable;

    }

    public List<String> getPkName() {
        return pkName;
    }

    public void setPkName(List<String> pkName) {
        this.pkName = pkName;
    }

    public List<String> getFkName() {
        return fkName;
    }

    public void setFkName(List<String> fkName) {
        this.fkName = fkName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getPktable() {
        return pktable;
    }

    public void setPktable(List<String> pktable) {
        this.pktable = pktable;
    }
}
