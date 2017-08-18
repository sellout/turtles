/*
 * Copyright 2014–2017 SlamData Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package turtles.data

import slamdata.Predef._
import turtles._

import scalaz._

trait OptionInstances {
  implicit def optionBirecursive[A]
      : Birecursive.Aux[Option[A], Const[Option[A], ?]] =
    id.idBirecursive[Option[A]]

  implicit val optionDelayEqual: Delay[Equal, Option] =
    new Delay[Equal, Option] {
      def apply[A](a: Equal[A]) = {
        implicit val aʹ: Equal[A] = a
        Equal[Option[A]]
      }
    }

  implicit val optionDelayOrder: Delay[Order, Option] =
    new Delay[Order, Option] {
      def apply[A](a: Order[A]) = {
        implicit val aʹ: Order[A] = a
        Order[Option[A]]
      }
    }

  implicit val optionDelayShow: Delay[Show, Option] =
    new Delay[Show, Option] {
      def apply[A](a: Show[A]) = {
        implicit val aʹ: Show[A] = a
        Show[Option[A]]
      }
    }
}

object option extends OptionInstances