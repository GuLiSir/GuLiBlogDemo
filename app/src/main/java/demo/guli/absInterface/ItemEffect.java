package demo.guli.absInterface;

/**
 * 小动画效果抽象接口
 */
public interface ItemEffect {
    /**
     * 执行播放动画前的准备工作
     */
    void prepare();

    /**
     * 开始播放该动画效果,动画播放完成的时候会主动播放监听,如果中途被停止则不会调用监听
     *
     * @param listener 监听
     */
    void startEffect(EffectListener listener);

    /**
     * 主动停止该动画效果
     */
    void endEffect();

    /**
     * 当前状态枚举
     */
    enum SelfEffectState {
        //处于初始化状态,什么都没有做
        NOTHINK,
        //准备状态,可以播放动画
        PREPARE,
        //正在执行动画
        ANIMATION
    }
}

