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
package com.iluwatar.iterator;

import static com.iluwatar.iterator.list.ItemType.ANY;
import static com.iluwatar.iterator.list.ItemType.POTION;
import static com.iluwatar.iterator.list.ItemType.RING;
import static com.iluwatar.iterator.list.ItemType.WEAPON;

import com.iluwatar.iterator.bst.BstIterator;
import com.iluwatar.iterator.bst.TreeNode;
import com.iluwatar.iterator.list.ItemType;
import com.iluwatar.iterator.list.TreasureChest;
import lombok.extern.slf4j.Slf4j;

/**
 * The Iterator pattern is a design pattern in which an iterator is used to traverse a container and
 * access the container's elements. The Iterator pattern decouples algorithms from containers.
 * 迭代器模式是一种设计模式，其中迭代器用于遍历容器并访问容器的元素。迭代器模式将算法与容器解耦。
 *
 * <p>In this example the Iterator ({@link Iterator}) adds abstraction layer on top of a collection
 * ({@link TreasureChest}). This way the collection can change its internal implementation without
 * affecting its clients.
 * 在这个例子中，迭代器({@link Iterator})在集合({@link TreasureChest})之上添加了抽象层。通过这种方式，集合可以更改其内部实现，而不影响其客户端。
 */
@Slf4j
public class App {

  private static final TreasureChest TREASURE_CHEST = new TreasureChest();

  private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
    LOGGER.info("------------------------");
    LOGGER.info("Item Iterator for ItemType " + itemType + ": ");
    var itemIterator = TREASURE_CHEST.iterator(itemType);
    while (itemIterator.hasNext()) {
      LOGGER.info(itemIterator.next().toString());
    }
  }

  private static void demonstrateBstIterator() {
    LOGGER.info("------------------------");
    LOGGER.info("BST Iterator: ");
    var root = buildIntegerBst();
    var bstIterator = new BstIterator<>(root);
    while (bstIterator.hasNext()) {
      LOGGER.info("Next node: " + bstIterator.next().getVal());
    }
  }

  private static TreeNode<Integer> buildIntegerBst() {
    var root = new TreeNode<>(8);

    root.insert(3);
    root.insert(10);
    root.insert(1);
    root.insert(6);
    root.insert(14);
    root.insert(4);
    root.insert(7);
    root.insert(13);

    return root;
  }

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    // 通过类型，演示宝箱的迭代器。
    demonstrateTreasureChestIteratorForType(RING);
    demonstrateTreasureChestIteratorForType(POTION);
    demonstrateTreasureChestIteratorForType(WEAPON);
    demonstrateTreasureChestIteratorForType(ANY);

    // 演示BST迭代器
    demonstrateBstIterator();
  }
}
