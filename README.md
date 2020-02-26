# The Ray Tracer Challenge

This repo holds the code for a ray tracer following this book: [The Ray Tracer Challenge - A Test-Driven Guide to Your First 3D Renderer](https://pragprog.com/book/jbtracer/the-ray-tracer-challenge)

## Getting started

This project uses [Scala 2.13](https://scala-lang.org/) and [SBT](https://www.scala-sbt.org/) version 1.3.8.

Once everything is installed, you should be able to run the following:

## Running the Main files

```
> mill.runMain org.bbstilson.raytracer.chapters.Chapter1
```

## Running the tests

From the project root:

```
> mill raytracerchallenge.test
```

### Running a specific suite

```
TBD. Mill [has a way](http://www.lihaoyi.com/post/MillBetterScalaBuilds.html) but I haven't figured it out yet.
```

### Running a specific test

Where a test looks like:

```scala
"Foo" should "do a thing" in {
  // blah
}
```

```
TBD. Mill [has a way](http://www.lihaoyi.com/post/MillBetterScalaBuilds.html) but I haven't figured it out yet.
```

## Putting it together

The end of a chapter typically came with some visual output. They can be viewed [here](./src/main/scala/org/bbstilson/raytracer/README.md).
