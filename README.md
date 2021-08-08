# chaostoolkit-springboot-demo
Demo springboot application with chaostoolkit integrated in ci

## Deploy Code / Infra

- Use releases to deploy infra and relase source code
- Semantic version convension used - vX.X.X
- Here X is a number, for e.g. v1.0.0 is on of the release

## Experiments

- Use releases to run experiments
- Following table explain which tag version release will run which type of experiment

|Type |Experiment |Tag version |
|:--|:--|:--|
|Application |Kill Application |chaos-kill-X|
|Application |Custom Exception |chaos-exc-X|
|Application |Latency |chaos-lat-X|
|Application |Memory load |chaos-mem-X|
|Infrastructure |Kill random ECS task |chaos-infra-X|

where X is a number which should be incremented for successive releases.
