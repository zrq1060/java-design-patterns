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
package com.iluwatar.abstractfactory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Abstract Factory pattern provides a way to encapsulate a group of individual factories that
 * have a common theme without specifying their concrete classes. In normal usage, the client
 * software creates a concrete implementation of the abstract factory and then uses the generic
 * interface of the factory to create the concrete objects that are part of the theme. The client
 * does not know (or care) which concrete objects it gets from each of these internal factories,
 * since it uses only the generic interfaces of their products. This pattern separates the details
 * of implementation of a set of objects from their general usage and relies on object composition,
 * as object creation is implemented in methods exposed in the factory interface.
 * 抽象工厂模式提供了一种方式来封装一组具有公共主题的独立工厂，而无需指定它们的具体类。在正常使用中，客户端软件创建抽象工厂的具体实现，
 * 然后使用工厂的通用接口创建作为主题一部分的具体对象。客户端不知道(或不关心)它从每个内部工厂获得了哪些具体对象，因为它只使用它们产品的通用接口。
 * 该模式将一组对象的实现细节与它们的一般用法分离，并依赖于对象组合，因为对象创建是在工厂接口中公开的方法中实现的。
 *
 * <p>The essence of the Abstract Factory pattern is a factory interface ({@link KingdomFactory})
 * and its implementations ( {@link ElfKingdomFactory}, {@link OrcKingdomFactory}). The example uses
 * both concrete implementations to create a king, a castle, and an army.
 */
@Slf4j
@Getter
public class App implements Runnable {

  private final Kingdom kingdom = new Kingdom();

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    var app = new App();
    app.run();
  }

  @Override
  public void run() {
    // 精灵
    LOGGER.info("elf kingdom");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());

    // 兽人
    LOGGER.info("orc kingdom");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());
  }

  /**
   * Creates kingdom.
   * @param kingdomType type of Kingdom
   */
  public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
    final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
    kingdom.setKing(kingdomFactory.createKing());
    kingdom.setCastle(kingdomFactory.createCastle());
    kingdom.setArmy(kingdomFactory.createArmy());
  }
}