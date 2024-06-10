package top.szzz666.quickBridger.entity;

import cn.nukkit.block.Block;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;

import java.util.ArrayList;

public class QBer {
    private String qbername;
    private Position qberSpawnPoint;
    private ArrayList<Block> qberBlock;
    private boolean isSetSpawnPoint = true;
    private boolean isSpeedup = true;
    private boolean isElevator = true;
    private boolean isStop = true;
    private boolean isReturn = true;
    private ArrayList<Item> qberInventory;

    public QBer() {
    }

    public QBer(String qbername, Position qberSpawnPoint, ArrayList<Block> qberBlock, boolean isSetSpawnPoint, boolean isSpeedup, boolean isElevator, boolean isStop, boolean isReturn, ArrayList<Item> qberInventory) {
        this.qbername = qbername;
        this.qberSpawnPoint = qberSpawnPoint;
        this.qberBlock = qberBlock;
        this.isSetSpawnPoint = isSetSpawnPoint;
        this.isSpeedup = isSpeedup;
        this.isElevator = isElevator;
        this.isStop = isStop;
        this.isReturn = isReturn;
        this.qberInventory = qberInventory;
    }


    // 添加玩家放置方块数据
    public void addQberBlock(Block block) {
        if (this.qberBlock == null) {
            this.qberBlock = new ArrayList<>();
        }
        this.qberBlock.add(block);
    }

    /**
     * 获取
     * @return qbername
     */
    public String getQbername() {
        return qbername;
    }

    /**
     * 设置
     * @param qbername
     */
    public void setQbername(String qbername) {
        this.qbername = qbername;
    }

    /**
     * 获取
     * @return qberSpawnPoint
     */
    public Position getQberSpawnPoint() {
        return qberSpawnPoint;
    }

    /**
     * 设置
     * @param qberSpawnPoint
     */
    public void setQberSpawnPoint(Position qberSpawnPoint) {
        this.qberSpawnPoint = qberSpawnPoint;
    }

    /**
     * 获取
     * @return qberBlock
     */
    public ArrayList<Block> getQberBlock() {
        return qberBlock;
    }

    /**
     * 设置
     * @param qberBlock
     */
    public void setQberBlock(ArrayList<Block> qberBlock) {
        this.qberBlock = qberBlock;
    }

    /**
     * 获取
     * @return isSetSpawnPoint
     */
    public boolean isIsSetSpawnPoint() {
        return isSetSpawnPoint;
    }

    /**
     * 设置
     * @param isSetSpawnPoint
     */
    public void setIsSetSpawnPoint(boolean isSetSpawnPoint) {
        this.isSetSpawnPoint = isSetSpawnPoint;
    }

    /**
     * 获取
     * @return isSpeedup
     */
    public boolean isIsSpeedup() {
        return isSpeedup;
    }

    /**
     * 设置
     * @param isSpeedup
     */
    public void setIsSpeedup(boolean isSpeedup) {
        this.isSpeedup = isSpeedup;
    }

    /**
     * 获取
     * @return isElevator
     */
    public boolean isIsElevator() {
        return isElevator;
    }

    /**
     * 设置
     * @param isElevator
     */
    public void setIsElevator(boolean isElevator) {
        this.isElevator = isElevator;
    }

    /**
     * 获取
     * @return isStop
     */
    public boolean isIsStop() {
        return isStop;
    }

    /**
     * 设置
     * @param isStop
     */
    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }

    /**
     * 获取
     * @return isReturn
     */
    public boolean isIsReturn() {
        return isReturn;
    }

    /**
     * 设置
     * @param isReturn
     */
    public void setIsReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * 获取
     * @return qberInventory
     */
    public ArrayList<Item> getQberInventory() {
        return qberInventory;
    }

    /**
     * 设置
     * @param qberInventory
     */
    public void setQberInventory(ArrayList<Item> qberInventory) {
        this.qberInventory = qberInventory;
    }

    public String toString() {
        return "QBer{qbername = " + qbername + ", qberSpawnPoint = " + qberSpawnPoint + ", qberBlock = " + qberBlock + ", isSetSpawnPoint = " + isSetSpawnPoint + ", isSpeedup = " + isSpeedup + ", isElevator = " + isElevator + ", isStop = " + isStop + ", isReturn = " + isReturn + ", qberInventory = " + qberInventory + "}";
    }
}
