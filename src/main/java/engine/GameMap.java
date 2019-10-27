package engine;

import engine.enumeration.ObjectStatus;
import engine.utils.RandomEx;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class GameMap extends GameObject {
    private Logger logger = LogManager.getLogger(GameMap.class);

    private double horizontalOffset = 5;
    private double verticalOffset = 5;
    private double motionSpeed = 1;

    private File mapFile;
    private ImageView mapImageView;
    private double width;
    private double height;
    private double x = 0;
    private double y = 0;

    /**
     * 水平方向的运动，1: 向右，-1: 向左
     */
    private int horizontalMotion = 0;

    /**
     * 竖直方向运动：1: 向上，-1: 向下
     */
    private int verticalMotion = 0;

    public GameMap(String mapFilePath) {
        File file = new File(mapFilePath);
        if (file.exists()) {
            mapFile = file;
            Image mapImage = new Image("file:" + mapFilePath);
            mapImageView = new ImageView(mapImage);
            width = Game.getInstance().getWidth();
            height = Game.getInstance().getHeight();
            mapImageView.setFitWidth(width + horizontalOffset * 3);
            mapImageView.setFitHeight(height + verticalOffset * 3);
        } else {
            logger.error("file {} is not exist!", mapFilePath);
        }
    }

    public GameMap(Image mapImage) {
        mapImageView = new ImageView(mapImage);
        width = Game.getInstance().getWidth();
        height = Game.getInstance().getHeight();
        mapImageView.setFitWidth(width + horizontalOffset * 3);
        mapImageView.setFitHeight(height + verticalOffset * 3);
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        logger.debug("map update x={} y={} hOffset={} vOffset={}", x, y, horizontalOffset, verticalOffset);
    }

    /**
     * 重设地图位置
     */
    public void reset() {
        x = 0;
        y = 0;
        logger.debug("map reset x={} y={} hOffset={} vOffset={}", x, y, horizontalOffset, verticalOffset);
    }

    @Override
    public void start() {
        status = ObjectStatus.ANIMATION;
        logger.info("map start");
    }

    @Override
    public void stop() {
        status = ObjectStatus.STOP;
        reset();
        logger.info("map stop");
    }

    public ObjectStatus getStatus() {
        return status;
    }

    public void setStatus(ObjectStatus status) {
        this.status = status;
    }

    public Image getMapImage() {
        return mapImageView.getImage();
    }

    public ImageView getMapImageView() {
        return mapImageView;
    }

    public File getMapFile() {
        return mapFile;
    }

    public double getHorizontalOffset() {
        return horizontalOffset;
    }

    public void setHorizontalOffset(double horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }

    public double getVerticalOffset() {
        return verticalOffset;
    }

    public void setVerticalOffset(double verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    public double getMotionSpeed() {
        return motionSpeed;
    }

    public void setMotionSpeed(double value) {
        this.motionSpeed = value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
