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
 * Lambda implementation for enum strategy pattern.
 * 枚举策略模式的Lambda实现。
 */
@Slf4j
public class LambdaStrategy {

  /**
   * Enum to demonstrate strategy pattern.
   * 枚举来演示策略模式。
   */
  public enum Strategy implements DragonSlayingStrategy {
    // 说明：由于此Strategy实现DragonSlayingStrategy，所以此execute()执行的时候，
    // 会执行枚举构造方法内的dragonSlayingStrategy的execute()，又因为是此接口是一个方法，
    // 所以可以使用Lambda表达式来简化实现，即会执行其内部的打印。

    // 近战策略（打印信息同：MeleeStrategy）
    MELEE_STRATEGY(() -> LOGGER.info(
        "With your Excalibur you sever the dragon's head!")),
    // 炮弹的策略（打印信息同：ProjectileStrategy）
    PROJECTILE_STRATEGY(() -> LOGGER.info(
        "You shoot the dragon with the magical crossbow and it falls dead on the ground!")),
    // 咒语的策略（打印信息同：SpellStrategy）
    SPELL_STRATEGY(() -> LOGGER.info(
        "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!"));

    private final DragonSlayingStrategy dragonSlayingStrategy;

    Strategy(DragonSlayingStrategy dragonSlayingStrategy) {
      this.dragonSlayingStrategy = dragonSlayingStrategy;
    }

    @Override
    public void execute() {
      dragonSlayingStrategy.execute();
    }
  }
}