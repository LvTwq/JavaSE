package com.example.designpatterns.machine;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/9/28 14:48
 */
public class VendingMachine {

    private VendingMachineState currentState;

    public VendingMachine() {
        currentState = VendingMachineState.IDLE;
    }


    // 处理事件并执行状态转换和动作
    public void handleEvent(VendingMachineEvent event) {
        switch (currentState) {
            case IDLE:
                if (event == VendingMachineEvent.COIN_INSERTED) {
                    currentState = VendingMachineState.ACCEPTING;
                    System.out.println("Accepting coins...");
                }
                break;

            case ACCEPTING:
                if (event == VendingMachineEvent.PRODUCT_SELECTED) {
                    currentState = VendingMachineState.SELLING;
                    System.out.println("Selling product...");
                }
                break;

            case SELLING:
                if (event == VendingMachineEvent.PRODUCT_SOLD) {
                    currentState = VendingMachineState.IDLE;
                    System.out.println("Thank you for your purchase.");
                }
                break;

            default:
                System.out.println("Invalid state");
        }
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // 模拟用户操作
        vendingMachine.handleEvent(VendingMachineEvent.COIN_INSERTED);
        vendingMachine.handleEvent(VendingMachineEvent.PRODUCT_SELECTED);
        vendingMachine.handleEvent(VendingMachineEvent.PRODUCT_SOLD);
    }
}


// 状态枚举
enum VendingMachineState {
    IDLE,          // 待机状态
    ACCEPTING,     // 接受货币状态
    SELLING        // 出售商品状态
}

// 事件枚举
enum VendingMachineEvent {
    COIN_INSERTED, // 插入货币事件
    PRODUCT_SELECTED, // 选择商品事件
    PRODUCT_SOLD   // 商品售出事件
}

/*
状态机（State Machine）是一个抽象的计算机科学概念，用于描述对象、程序或系统在不同状态之间的转换和行为。状态机由一组状态、状态之间的转换规则以及与状态相关联的动作组成。

以下是状态机的一些关键概念和要素：

状态（State）：状态是对象或系统可能处于的不同情况或状态的描述。例如，在一个自动售货机中，状态可以包括 "待机"、"接受货币"、"出售商品" 等。状态用来描述对象的属性和行为。

转换（Transition）：转换表示状态之间的变化或切换。它定义了从一个状态到另一个状态的条件或事件。转换通常与触发条件相关，当条件满足时，状态机会从一个状态转移到另一个状态。

事件（Event）：事件是触发状态转换的外部或内部输入。事件可以是来自用户的输入、传感器的信号、定时器的触发等。事件通常与状态机的转换规则相关。

动作（Action）：动作是与状态相关联的操作或行为。在状态转换发生时，状态机可以执行特定的动作，例如发送通知、记录日志、修改对象的属性等。

初始状态（Initial State）：初始状态是状态机的起始状态，表示状态机的初始状态。状态机在启动时通常从初始状态开始。

终止状态（Final State）：终止状态表示状态机的结束状态，它指示状态机已完成其任务或已达到其最终状态。


有两种主要类型的状态机：

有限状态机（Finite State Machine，FSM）：有限状态机具有有限数量的状态，每个状态之间的转换都是明确定义的。它们常用于描述离散事件系统和自动控制系统。

无限状态机（Infinite State Machine）：无限状态机具有无限数量的状态，状态之间的转换不一定是明确定义的。它们常用于描述连续事件系统和自适应系统。
 */