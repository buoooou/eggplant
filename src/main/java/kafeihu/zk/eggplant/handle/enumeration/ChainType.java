package kafeihu.zk.eggplant.handle.enumeration;

public enum ChainType {

    ONLYONE("only_one","只有一个任务处理完毕即可"),
    FULLALL("full_all","所有任务都需处理完毕")
    ;

    private String code;
    private String message;
    ChainType(String code,String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
