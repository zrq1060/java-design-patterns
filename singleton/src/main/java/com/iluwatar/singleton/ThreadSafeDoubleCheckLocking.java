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
package com.iluwatar.singleton;

/**
 * <p>Double check locking.</p>
 *
 * <p>http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html</p>
 *
 * <p>Broken under Java 1.4.</p>
 *
 */
public final class ThreadSafeDoubleCheckLocking {
  /**
   * Singleton instance of the class, declared as volatile to ensure atomic access by multiple threads.
   */
  private static volatile ThreadSafeDoubleCheckLocking instance;

  /**
   * private constructor to prevent client from instantiating.
   */
  private ThreadSafeDoubleCheckLocking() {
    // to prevent instantiating by Reflection call
    if (instance != null) {
      throw new IllegalStateException("Already initialized.");
    }
  }

  /**
   * Public accessor.
   *
   * @return an instance of the class.
   */
  public static ThreadSafeDoubleCheckLocking getInstance() {
    // local variable increases performance by 25 percent
    // Joshua Bloch "Effective Java, Second Edition", p. 283-284
    // 本地变量增加25%的性能,Joshua Bloch《Effective Java, Second Edition》，第283-284页

    var result = instance;
    // Check if singleton instance is initialized.
    // If it is initialized then we can return the instance.
    if (result == null) {
      // It is not initialized, but we cannot be sure because some other thread might have
      // initialized it in the meanwhile.
      // 它没有初始化，但我们不能确定，因为其他线程可能已经在同时初始化了它。
      // So to make sure we need to lock on an object to get mutual exclusion.
      // 为了确保我们需要锁定一个对象来获得互斥。
      synchronized (ThreadSafeDoubleCheckLocking.class) {
        // Again assign the instance to local variable to check if it was initialized by some
        // other thread while current thread was blocked to enter the locked zone.
        // 再次将实例赋值给局部变量，以检查当当前线程被阻塞进入锁定区域时，它是否被其他线程初始化。

        // If it was initialized then we can return the previously created instance
        // just like the previous null check.
        // 如果它已经初始化，那么我们可以返回先前创建的实例，就像先前的null检查一样。
        result = instance;
        if (result == null) {
          // The instance is still not initialized, so we can safely
          // (no other thread can enter this zone)
          // create an instance and make it our singleton instance.
          // 实例仍然没有初始化，所以我们可以安全地(没有其他线程可以进入这个区域)创建一个实例，使它成为我们的单例实例。
          result = new ThreadSafeDoubleCheckLocking();
          instance = result;
        }
      }
    }
    return result;
  }
}
