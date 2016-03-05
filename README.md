[![Build Status](https://travis-ci.org/slamdata/matryoshka.svg?branch=master)](https://travis-ci.org/slamdata/matryoshka)
[![codecov.io](https://codecov.io/github/slamdata/matryoshka/coverage.svg?branch=master)](https://codecov.io/github/slamdata/matryoshka?branch=master)
[![Join the chat at https://gitter.im/slamdata/matryoshka](https://badges.gitter.im/slamdata/matryoshka.svg)](https://gitter.im/slamdata/matryoshka?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# Matryoshka

This library provides generalized folds, unfolds, and traversals for fixed point data. structures in Scala.

## Introduction

This library is predicated on the idea of rewriting your recursive data structures as functors where the recursive component is replaced by a parameter.

```scala
sealed trait Expr
final case class Var(name: String)          extends Expr
final case class Lam(v: String, body, Expr) extends Expr
final case class App(f: Expr, arg: Expr)    extends Expr
```
could be rewritten as

```scala
sealed trait Expr[A]
final case class Var[A](name: String)       extends Expr[A]
final case class Lam[A](v: String, body, A) extends Expr[A]
final case class App[A](f: A, arg: A)       extends Expr[A]
```

Then you use one of the fixed point type constructors below to regain your recursive type.

### Fixpoint Types

These types take a one-arg type constructor (a functor) and provide a recursive form of it.

All of these types have instances for `Recursive`, `Corecursive`, `FunctorT`, `TraverseT`, `Equal`, `Show`, and `Arbitrary` unless otherwise noted.

#### `Fix`

This is the simplest fixpoint type, implemented with general recursion.

#### `Mu`

This is for inductive (finite) recursive structures, models the concept of “data”.

#### `Nu`

This is for coinductive (potentially infinite) recursive structures, models the concept of “codata”.

#### `Cofree[?[_], A]`

Does not have a `Corecursive` instance. This represents a structure with some metadata attached to each node.

In addition to the usual operations, it can also be folded using an Elgot algebra.

#### `Free[?[_], A]`

Does not have a `Recursive` instance.

In addition to the usual operations, it can also be created by unfolding with an Elgot coalgebra.

### Algebras

A structure like this makes it possible to separate recursion from your operations. You can now write transformations that operate on only a single node of your structure at a time.

![algebras and coalgebras](resources/algebras.png)

This diagram covers the major classes of transformations. The most basic ones are in the center and the arrows show how they can be generalized in various ways.

### Folds

Those algebras can be applied recursively to your structures using many different folds. Here is a cheat-sheet (also available [in PDF](resources/recursion-schemes.pdf)) for some of them.

![folds and unfolds](resources/recursion-schemes.png)
