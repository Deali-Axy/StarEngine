package engine;

import engine.enumeration.GameStatus;
import engine.manager.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Game {
    private static Game instance;

    public static final String name = "CRAZY ALPHA!";
    public static final String author = "DealiAxy";
    public static final String version = "1.0.1";

    private Logger logger = LogManager.getLogger(Game.class);
    private AnimationTimer animationTimer;
    private EventManager eventManager = new EventManager();
    private MapManager mapManager = new MapManager();
    private ModelManager modelManager = new ModelManager();
    private ResouceManager resouceManager = new ResouceManager();
    private DataManager dataManager = new DataManager();
    private Render render = new Render();

    private GameStatus status = GameStatus.STOP;
    private int score;  // 上次游戏得分

    private double width;
    private double height;

    // scene
    private Scene gameScene;

    private Game() {
        // todo 初始化背景音乐
//        backgroundMusic = new MediaPlayer(resouceManager.getMedia("background"));
//        backgroundMusic.setVolume(0.5);
//        backgroundMusic.setAutoPlay(true);
//        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * 输出调试信息
     *
     * @param template 　字符串
     * @param args     　参数
     */
    public void debug(String template, Object... args) {
        String text = "";
        for (Object object : args) {
            text = template.replace("{}", String.valueOf(object));
        }

        logger.debug(text);
        // todo 输出到游戏画面
    }

    public void pause() {
        setStatus(GameStatus.PAUSE);
        mapManager.next();
//        render.init(new PauseView());
    }

    public void resume() {
        if (status == GameStatus.PAUSE) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init(gameScene);
            eventManager.refresh();
        }
    }

    public void start() {
        if (status == GameStatus.STOP) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init();
            gameScene = render.getScene();
            eventManager.refresh();
        } else if (status == GameStatus.OVER) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init(gameScene);
            eventManager.refresh();
        }
    }

    public void exit() {
        new AnimationTimer() {
            long time = System.currentTimeMillis();

            @Override
            public void handle(long l) {
                if (System.currentTimeMillis() - time < 550) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                } else
                    System.exit(0);
            }
        }.start();
    }

    public ResouceManager getResourceManager() {
        return resouceManager;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public Render getRender() {
        return render;
    }

    public Scene getScene() {
        return render.getScene();
    }

    public EventManager getEventManager() {
        return eventManager;
    }


    public MapManager getMapManager() {
        return mapManager;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus setStatus) {
        switch (setStatus) {
            case STOP:
            case PAUSE:
            case OVER:
                animationTimer.stop();
                break;
            case RUNNING:
                animationTimer.start();
        }

        this.status = setStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
