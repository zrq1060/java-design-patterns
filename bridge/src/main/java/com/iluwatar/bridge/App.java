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
package com.iluwatar.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * Composition over inheritance. The Bridge pattern can also be thought of as two layers of
 * abstraction. With Bridge, you can decouple an abstraction from its implementation so that the two
 * can vary independently.
 * 组合大于继承。桥接模式也可以看作是两个抽象层。使用Bridge，您可以将抽象与其实现解耦，以便两者可以独立变化。
 *
 * <p>In Bridge pattern both abstraction ({@link Weapon}) and implementation ( {@link Enchantment})
 * have their own class hierarchies. The interface of the implementations can be changed without
 * affecting the clients.
 * 在桥模式中，抽象({@link Weapon})和实现({@link Enchantment})都有自己的类层次结构。可以在不影响客户机的情况下更改实现的接口。
 *
 * <p>In this example we have two class hierarchies. One of weapons and another one of
 * enchantments. We can easily combine any weapon with any enchantment using composition instead of
 * creating deep class hierarchy.
 * 在这个例子中，我们有两个类层次结构。一个是武器，一个是魔法。我们可以很容易地使用合成将任何武器和任何附魔结合起来，而不是创建深层次的职业层次。
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    // 武器加魔法
    // 骑士得到一把施了魔法的剑。
    LOGGER.info("The knight receives an enchanted sword.");
    var enchantedSword = new Sword(new SoulEatingEnchantment());
    enchantedSword.wield();
    enchantedSword.swing();
    enchantedSword.unwield();

    // 女武神得到一把施了魔法的锤子。
    LOGGER.info("The valkyrie receives an enchanted hammer.");
    var hammer = new Hammer(new FlyingEnchantment());
    hammer.wield();
    hammer.swing();
    hammer.unwield();
  }
}
