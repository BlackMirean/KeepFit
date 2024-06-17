package com.rgzn.zt.entity;

import com.rgzn.zt.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    public static List<ActionInfo> getListData(int position){
        List<ActionInfo> list = new ArrayList<>();

        if (position == 0){//胸部
            list.add(new ActionInfo(0, R.drawable.train_right,
                    "俯身哑铃飞鸟",
                    "动作描述：站在两只哑铃的两侧，双腿微屈，上半身前倾，背部挺直。双手持哑铃，手心朝前，肘部微曲，然后将哑铃向两侧展开，直到感觉胸部有紧张感，然后慢慢合拢哑铃回到起始位置。\n" +
                            "注意事项：保持背部挺直，动作稳健，避免用力过大导致伤害。",
                    1));

            list.add(new ActionInfo(1, R.drawable.train_right,
                    "卧推",
                    "动作描述：躺在平板卧推架上，双手握住哑铃或者杠铃，慢慢将重量推举起来，直到手臂伸直，然后再慢慢降低到胸部，保持背部的贴合。\n" +
                            "注意事项：控制动作幅度，保持稳定的重量，注意呼吸。",
                    1));

            list.add(new ActionInfo(2, R.drawable.train_right,
                    "俯卧撑",
                    "动作描述：俯卧在地上，双手与肩同宽，手指朝前，脚尖着地，整个身体呈一条直线。然后慢慢弯曲肘部，将身体降低到地面，再推举起来。\n" +
                            "注意事项：保持身体一直线，不要塌腰或者抬臀。",
                    1));

            list.add(new ActionInfo(3, R.drawable.train_right,
                    "器械夹胸",
                    "动作描述：坐在夹胸器械的座椅上，双手握住器械的把手，背部挺直。然后慢慢将两侧的把手合拢，用胸部的力量夹住器械，再慢慢展开。\n" +
                            "注意事项：控制动作速度，确保胸部在夹合的过程中有紧张感。",
                    1));

        }else if (position == 1){//背部
            list.add(new ActionInfo(10, R.drawable.train_right,
                    "引体向上",
                    "动作描述：挂在高的横杠上，双手握住横杠，手心朝外，双肘微曲。用背部和手臂的力量，将身体拉升到横杠上，直到下巴超过横杠，然后慢慢降低身体至完全伸直。\n" +
                            "注意事项：保持动作的控制，尽量使用背部肌肉进行拉升。",
                    1));

            list.add(new ActionInfo(11, R.drawable.train_right,
                    "哑铃划船",
                    "动作描述：单腿跪在椅子上，另一腿伸直放地上，一只手握住哑铃，身体前倾。然后用背部力量，将哑铃向身体拉近，再慢慢放开。\n" +
                            "注意事项：保持身体前倾，拉力集中在背部。",
                    1));

            list.add(new ActionInfo(12, R.drawable.train_right,
                    "杠铃划船",
                    "动作描述：双腿微屈，上半身前倾，背部挺直。双手握住杠铃，杠铃放在膝盖前。然后用背部力量，将杠铃向身体拉近，再慢慢放开。\n" +
                            "注意事项：保持背部挺直，避免用力过大。",
                    1));

        }else if (position == 2){//腿部
            list.add(new ActionInfo(20, R.drawable.train_right,
                    "深蹲",
                    "动作描述：双腿自然分开，脚尖稍微外展。保持背部挺直，屈膝下蹲，直到大腿与地面平行，然后用腿部力量将身体推起。\n" +
                            "注意事项：保持膝盖与脚尖方向一致，避免膝盖过度弯曲。",
                    1));
            list.add(new ActionInfo(21, R.drawable.train_right,
                    "腿举",
                    "动作描述：站立或坐在器械上，双腿伸直。然后用腿部力量，将双腿抬起至与地面平行，再慢慢放下。\n" +
                            "注意事项：保持动作的稳定性，避免使用腰部的力量。",
                    1));
            list.add(new ActionInfo(22, R.drawable.train_right,
                    "腿弯举",
                    "动作描述：站立或坐在器械上，双腿伸直。然后用腿部力量，将双腿弯曲，将脚尖抬起至臀部，再慢慢放下。\n" +
                            "注意事项：保持动作的稳定性，避免使用腰部的力量。",
                    1));
            list.add(new ActionInfo(23, R.drawable.train_right,
                    "腿后弯",
                    "动作描述：站立或坐在器械上，双腿伸直。然后用腿部力量，将上半身前倾，将脚尖抬起，然后慢慢放下。\n" +
                            "注意事项：保持动作的控制，避免用力过大。",
                    1));
        }else if (position == 3){//腹部
            list.add(new ActionInfo(30, R.drawable.train_right,
                    "仰卧起坐",
                    "动作描述：仰卧在地上，双腿弯曲，双手交叉放在胸前或头后，然后用腹肌的力量将上半身抬起，再缓慢放下。\n" +
                            "注意事项：避免用颈部过多的力量，保持动作的稳定性。",
                    1));

            list.add(new ActionInfo(31, R.drawable.train_right,
                    "卷腹",
                    "动作描述：仰卧在地上，双腿伸直。然后用腹肌的力量，将双腿抬起，再慢慢放下。\n" +
                            "注意事项：保持腹肌的收缩，避免用背部的力量。",
                    1));
            list.add(new ActionInfo(32, R.drawable.train_right,
                    "侧卧腹肌训练",
                    "动作描述：侧卧在地上，用一只手支撑头部，另一只手放在身体前面。然后用腹肌的力量将上半身抬起，再慢慢放下。\n" +
                            "注意事项：保持动作的平稳，避免用手臂的力量。",
                    1));
            list.add(new ActionInfo(33, R.drawable.train_right,
                    "反向卷腹",
                    "动作描述：仰卧在地上，双腿伸直。然后将双腿抬起，再用腹肌的力量将臀部抬起，形成一个“V”字形，最后慢慢放下。\n" +
                            "注意事项：保持腹肌的收缩，避免用背部的力量。",
                    1));
            list.add(new ActionInfo(34, R.drawable.train_right,
                    "仰卧腿举",
                    "动作描述：仰卧在地上，双腿伸直。然后用腹肌的力量，将双腿抬起至垂直方向，再缓慢放下。\n" +
                            "注意事项：保持动作的平稳，避免用腰部的力量。",
                    1));
        }else if (position == 4){//肩部
            list.add(new ActionInfo(40, R.drawable.train_right,
                    "哑铃推举",
                    "动作描述：坐在凳子上或者站立，手持哑铃，手心朝前，然后将哑铃推举至头顶，再缓慢放下。\n" +
                            "注意事项：保持动作的平稳，避免用背部的力量。",
                    1));
            list.add(new ActionInfo(41, R.drawable.train_right,
                    "侧平举",
                    "动作描述：站立，手持哑铃，双腿自然分开。然后将手臂侧向两侧抬起，直至水平，再缓慢放下。\n" +
                            "注意事项：避免用身体的摆动，保持动作的规范。",
                    1));
            list.add(new ActionInfo(42, R.drawable.train_right,
                    "颈后推举",
                    "动作描述：坐在凳子上，手持哑铃，将哑铃放在颈后。然后将哑铃推举至头顶，再缓慢放下。\n" +
                            "注意事项：动作过程中保持颈部的稳定，避免用背部的力量。",
                    1));
            list.add(new ActionInfo(43, R.drawable.train_right,
                    "正面推举",
                    "动作描述：站立，手持哑铃，双腿自然分开。然后将手臂前方抬起，直至水平，再缓慢放下。\n" +
                            "注意事项：避免用身体的摆动，保持动作的规范。",
                    1));
            list.add(new ActionInfo(44, R.drawable.train_right,
                    "倒立飞鸟",
                    "动作描述：俯卧在斜板上，手持哑铃，双手自然垂直。然后将手臂侧向两侧抬起，直至水平，再缓慢放下。\n" +
                            "注意事项：保持动作的平稳，避免用背部的力量。",
                    1));

        }else{//手臂
            list.add(new ActionInfo(50, R.drawable.train_right,
                    "哑铃弯举",
                    "动作描述：站立，手持哑铃，双腿自然分开。然后将手臂弯曲，将哑铃从大腿处抬起至肩膀高度，再缓慢放下。\n" +
                            "注意事项：保持上臂固定，避免用身体摆动。",
                    1));

            list.add(new ActionInfo(51, R.drawable.train_right,
                    "杠铃颈后臂屈伸",
                    "动作描述：坐在凳子上，手持杠铃，将杠铃放在颈后。然后将手臂屈伸，将杠铃举起至直臂，再缓慢放下。\n" +
                            "注意事项：保持上臂固定，动作过程中颈部保持平稳。",
                    1));
            list.add(new ActionInfo(52, R.drawable.train_right,
                    "窄距俯身哑铃划船",
                    "动作描述：俯身于凳子上，手持哑铃，双脚自然分开。然后将手臂划动，将哑铃拉至腰部，再缓慢放下。\n" +
                            "注意事项：保持身体的平衡，避免用背部过多的力量。",
                    1));
            list.add(new ActionInfo(53, R.drawable.train_right,
                    "杠铃卧推",
                    "动作描述：躺在卧推凳上，手握杠铃，将杠铃推举至胸部，再缓慢放下。\n" +
                            "注意事项：保持手腕、肘关节和肩膀的稳定，避免用腰部的力量。",
                    1));

        }
        return list;
    }

}
