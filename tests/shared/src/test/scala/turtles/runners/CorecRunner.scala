/* Copyright 2014–2018 SlamData Inc. and Greg Pfeil.
 * Licensed under the Apache License, Version 2.0.
 * See https://github.com/sellout/turtles#copyright for details.
 */

package turtles.runners

import turtles._

import cats._
import org.scalatest._

abstract class CorecRunner[M[_], F[_], A] {
  def run[T: Eq: Show](implicit T: Birecursive.Aux[T, F])
      : A => Assertion
}
