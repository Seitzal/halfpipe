# halfpipe
A skat game server

Will probably only be localised in German, but we shall see...

## Build instructions

The core library and server are built using [Mill](https://github.com/lihaoyi/mill).

The client pulls in its dependencies via CDN and does not have its own build step.

### Linux

```bash
git clone https://github.com/seitzal/halfpipe
cd halfpipe
chmod +x ./mill
./mill -w server.runBackground
```
