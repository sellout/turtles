/*
 * Copyright 2014–2016 SlamData Inc.
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

package matryoshka

import scalaz._

sealed class ElgotAlgebraMOps[W[_], M[_], F[_], A](
  self: ElgotAlgebraM[W, M, F, A]) {

  def attribute(implicit W: Comonad[W], M: Functor[M], F: Functor[F]):
      ElgotAlgebraM[W, M, F, Cofree[F, A]] =
    algebra.attributeElgotM[W, M, F, A](self)
}
