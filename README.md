# The Ray Tracer Challenge

This repo holds the code for a ray tracer following this book: [The Ray Tracer Challenge - A Test-Driven Guide to Your First 3D Renderer](https://pragprog.com/book/jbtracer/the-ray-tracer-challenge)


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
