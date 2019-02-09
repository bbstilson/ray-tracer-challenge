# The Ray Tracer Challenge

This repo holds the code for a ray tracer following this book: [The Ray Tracer Challenge - A Test-Driven Guide to Your First 3D Renderer](https://pragprog.com/book/jbtracer/the-ray-tracer-challenge)

## Getting started

This project uses [Scala](https://scala-lang.org/) version 2.12.8 and [SBT](https://www.scala-sbt.org/) version 1.2.8. If you're on OSX, both can be installed via [Homebrew](https://brew.sh/).

Once everything is installed, you should be able to run the following:

```
> sbt update
```

## Running the Main files

```
> sbt
> runMain org.bbstilson.raytracer.Chapter1
```

## Running the tests

From the project root:

```
> sbt
> test
```

### Running a specific suite

```
> sbt
> test:testOnly *SomeSpec
```

### Running a specific test

Where a test looks like:

```scala
"Foo" should "do a thing" in {
  // blah
}
```

```
> sbt
> test:testOnly *SomeSpec -- -z "do a thing"
```

## Putting it together

The end of a chapter typically came with some visual output. They can be viewed [here](./src/main/scala/org/bbstilson/raytracer/README.md).
