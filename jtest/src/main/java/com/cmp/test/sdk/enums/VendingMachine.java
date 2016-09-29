package com.cmp.test.sdk.enums;

import com.google.common.base.Enums;

/**
 * Created by breez on 2016/01/22.
 */
public class VendingMachine {

    //当前运行状态
    private static State state = State.RESTING;
    //当前余额
    private static int amount = 0;
    //当前选择商品
    private static Input selection = null;

    /**持续状态，不能做其他操作**/
    enum StateDuration{TRANSIENT}

    /**
     * 运行状态
     */
    enum State{
        /**初始界面**/
        RESTING{
            void next(Input input){
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("放入金额："+input.amount()+"美分");
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                    default:
                        state = RESTING;
                        break;
                }
            }
        },
        /**选择商品**/
        ADDING_MONEY{
            void next(Input input){
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        System.out.println("再次放入金额："+input.amount()+"美分，您的余额是："+amount+"美分");
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        System.out.println("选择商品："+input);
                        if(amount < input.amount()){
                            System.out.println("你的余额不够购买商品："+input);
                            state = ADDING_MONEY;
                        }else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                        break;
                    default:
                        state = ADDING_MONEY;
                        break;
                }
            }
        },
        /**发出商品，交易成功**/
        DISPENSING(StateDuration.TRANSIENT){
            void next(){
                System.out.println("交易成功！请拿好您的商品："+selection);
                //扣除购买商品的金额
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        /**找零**/
        GIVING_CHANGE(StateDuration.TRANSIENT){
            void next(){
                if(amount > 0){
                    System.out.println("请拿好您的找零："+amount+"美分");
                    amount = 0;
                }
                state = TERMINAL;
            }
        },
        /**交易终止**/
        TERMINAL{
            void output(){
                System.out.println("交易结束");
            }
        };

        private boolean isTransient = false;

        /**当前是否是瞬时状态（即不可以做其他操作）**/
        public boolean isTransient(){return this.isTransient;}

        State(){}

        State(StateDuration stateDuration){this.isTransient = true;}

        //默认方法（在瞬时状态时做其他操作时被调用）
        void next(Input input){ System.out.println("该状态不能做其他操作！");}
        //默认方法（在非瞬时状态时不做操作时被调用）
        void next(){ System.out.println("请选择一个操作！");}
        //默认方法（查看余额）
        void output(){System.out.println("您的余额还剩："+amount+"美分");}
    }

    //执行一个操作
    public static void run(Input gen){
        if(state!=State.TERMINAL){
            if(state.isTransient()){
                state.next();
            }else{
                state.next(gen);
            }

        }else{
            state.output();
        }

    }

    //测试
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int i = 0;
        while(true){
            switch (state) {
                case RESTING:
                    //run(Enums.random(Category.MONEY.getValues()));
                    break;
                case ADDING_MONEY:
                    //如果金额不足
                    if(i > 0){
                        //run(Enums.random(Category.MONEY.getValues()));
                        i = 0;
                    }else{
                        //run(Enums.random(Category.ITEM_SELECTION.getValues()));
                        i++;
                    }
                    break;
                case TERMINAL:
                    run(Input.STOP);
                    return;
                default:
                    run(null);
                    break;
            }
        }
    }
}