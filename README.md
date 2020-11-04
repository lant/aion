# Aion
A small utility to display timestamps.

This is a small command line utility to translate timestamps (seconds, milliseconds, ...) into 
more readable date formats. 

So, instead of having to open a web browser tab to [epoch converter](https://www.epochconverter.com/), or invoke
the UNIX date command with esoteric parameters you can just use this. 

It works like this:
```batch
➜  releaseExecutable git:(main) ✗ ./aion.kexe
Parsing dates and timestamps. Use 'q', 'quit' or 'bye' to exit
time > 1604521781
- Original format: SECOND
- Date: 2020-11-04T20:29:41Z
time > 1604521781000
- Original format: MILLISECOND
- Date: 2020-11-04T20:29:41Z
time > 1604521781000000
- Original format: MICROSECOND
- Date: 2020-11-04T20:29:41Z
time > 1604521781000000000
- Original format: NANOSECOND
- Date: 2020-11-04T20:29:41Z
time > q
```

## Building
I used Kotlin native to code the utility. It builds a small binary, which is pretty fast to invoke. 

If you want to build it for your platform you just need to use the default gradle build task: 

```bash
./gradlew build
```

This will download the kotlin compiler for your platform and build the binary. It will be in: `./build/bin/native/releasesExecutable/aion.kexe`, 
just move it and rename it to your `$HOME/bin/` directory and that's pretty much it.

