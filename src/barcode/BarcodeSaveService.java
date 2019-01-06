package barcode;

/**
 *条形码保存服�?
 * @author ysc
 */
public interface BarcodeSaveService {
    /**
     * 保存条形�?
     * @param barcode 
     */
    public void save(String barcode);
    /**
     * 在这里释放资源，如数据库连接，关闭文件，关闭网络连接�?
     */
    public void finish();
}
