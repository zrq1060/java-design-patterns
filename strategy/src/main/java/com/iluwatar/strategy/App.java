/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * <p>The Strategy pattern (also known as the policy pattern) is a software design pattern that
 * enables an algorithm's behavior to be selected at runtime.</p>
 * 策略模式(也称为策略模式)是一种软件设计模式，它允许在运行时选择算法的行为。
 *
 * <p>Before Java 8 the Strategies needed to be separate classes forcing the developer
 * to write lots of boilerplate code. With modern Java, it is easy to pass behavior
 * with method references and lambdas making the code shorter and more readable.</p>
 * 在Java 8之前，策略需要是独立的类，迫使开发人员编写大量样板代码。使用现代Java，可以很容易地通过方法引用和lambda传递行为，这使得代码更短，可读性更强
 *
 * <p>In this example ({@link DragonSlayingStrategy}) encapsulates an algorithm. The containing
 * object ({@link DragonSlayer}) can alter its behavior by changing its strategy.</p>
 * 在这个例子中({@link DragonSlayingStrategy})封装了一个算法。包含的对象({@link DragonSlayer})可以通过改变策略来改变其行为
 *
 */
@Slf4j
public class App {

  private static final String RED_DRAGON_EMERGES = "Red dragon emerges.";
  private static final String GREEN_DRAGON_SPOTTED = "Green dragon spotted ahead!";
  private static final String BLACK_DRAGON_LANDS = "Black dragon lands before you.";

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    // GoF Strategy pattern
    // GoF战略模式

    // 前方发现绿龙！
    LOGGER.info(GREEN_DRAGON_SPOTTED);
    // 使用近战的策略
    var dragonSlayer = new DragonSlayer(new MeleeStrategy());
    dragonSlayer.goToBattle();
    // 红龙出现了。
    LOGGER.info(RED_DRAGON_EMERGES);
    // 使用炮弹的策略
    dragonSlayer.changeStrategy(new ProjectileStrategy());
    dragonSlayer.goToBattle();
    // 黑龙在你之前登陆。
    LOGGER.info(BLACK_DRAGON_LANDS);
    // 使用咒语的策略
    dragonSlayer.changeStrategy(new SpellStrategy());
    dragonSlayer.goToBattle();

    // Java 8 functional implementation Strategy pattern
    // Java 8功能实现策略模式（功能同上）
    LOGGER.info(GREEN_DRAGON_SPOTTED);
    dragonSlayer = new DragonSlayer(
        () -> LOGGER.info("With your Excalibur you sever the dragon's head!"));
    dragonSlayer.goToBattle();
    LOGGER.info(RED_DRAGON_EMERGES);
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "You shoot the dragon with the magical crossbow and it falls dead on the ground!"));
    dragonSlayer.goToBattle();
    LOGGER.info(BLACK_DRAGON_LANDS);
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!"));
    dragonSlayer.goToBattle();

    // Java 8 lambda implementation with enum Strategy pattern
    // Java 8 lambda实现与enum策略模式（功能同上）
    LOGGER.info(GREEN_DRAGON_SPOTTED);
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.MELEE_STRATEGY);
    dragonSlayer.goToBattle();
    LOGGER.info(RED_DRAGON_EMERGES);
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.PROJECTILE_STRATEGY);
    dragonSlayer.goToBattle();
    LOGGER.info(BLACK_DRAGON_LANDS);
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.SPELL_STRATEGY);
    dragonSlayer.goToBattle();
  }
}