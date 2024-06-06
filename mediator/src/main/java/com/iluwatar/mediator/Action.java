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
package com.iluwatar.mediator;

import lombok.Getter;

/**
 * Action enumeration.
 */
public enum Action {
  // 猎杀：title = 猎杀一只兔子，description = 来吃晚饭
  HUNT("hunted a rabbit", "arrives for dinner"),
  // 故事：title = 讲述一个故事，description = 过来听
  TALE("tells a tale", "comes to listen"),
  // 黄金：title = 发现黄金，description = 拿走了他的那份金子
  GOLD("found gold", "takes his share of the gold"),
  // 敌人：title = 发现敌人，description = 躲避
  ENEMY("spotted enemies", "runs for cover"),
  // 无：title = ，description =
  NONE("", "");

  private final String title;
  @Getter
  private final String description;

  Action(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String toString() {
    return title;
  }
}
