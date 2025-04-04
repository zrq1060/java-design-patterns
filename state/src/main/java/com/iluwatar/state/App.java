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
package com.iluwatar.state;

/**
 * In the State pattern, the container object has an internal state object that defines the current
 * behavior. The state object can be changed to alter the behavior.
 * 在State模式中，容器对象有一个定义当前行为的内部状态对象。可以通过改变状态对象来改变行为。
 *
 * <p>This can be a cleaner way for an object to change its behavior at runtime without resorting
 * to large monolithic conditional statements and thus improves maintainability.
 * 对于对象来说，这是一种更清晰的方式，可以在运行时改变其行为，而无需求助于大型单块条件语句，从而提高了可维护性。
 *
 * <p>In this example the {@link Mammoth} changes its behavior as time passes by.
 * 在这个例子中，随着时间的推移，{@link Mammoth}会改变它的行为。
 */
public class App {

  /**
   * Program entry point.
   */
  public static void main(String[] args) {

    // 长毛象
    var mammoth = new Mammoth();
    // 观察当前状态（内部操作的是状态的观察）
    mammoth.observe();
    // 改变愤怒的状态、和平的状态。（内部操作的是状态的进入）
    mammoth.timePasses();
    mammoth.observe();
    mammoth.timePasses();
    mammoth.observe();
  }
}
