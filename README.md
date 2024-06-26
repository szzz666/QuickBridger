# QuickBridger
### 一款简单的搭路练习nukkit插件

### 下载：https://www.minebbs.com/resources/quickbridger.8457/?ref=3785

### 搭路练习插件配置指南

### 搭路练习的玩家数量tips和rsnpc变量：{QbNumOfPlayers}

### 文件放置步骤
1. **文件夹位置**: 将**搭路练习世界文件夹**复制到 `plugins\QuickBridger\world` 目录中。
2. **配置调整**: 根据实际需求，自定义修改配置文件参数。

### 配置项详解

#### 搭建材料
- **BridgingBlock**: 搭路用方块，格式为 `物品ID:数据值:数量`，例："24:0:64" 使用无特殊值的石头，每次提供64个。

#### 关键方块设置
- **StopBlock**: 结束点方块ID，玩家触达即完成，设为 `152`。
- **ResBlock**: 重生点标记方块ID，为 `133`。
- **SpeedupBlock**: 加速方块，踩上加速，ID为 `41`。
- **ReturnBlock**: 返回出生点方块，ID `22`。
- **ElevatorBlock**: 电梯方块，用于快速上下，ID `138`。
- **Pickaxe**: 提供玩家的镐子，示例："278:0:1" 为无附魔石镐，1个。

#### 游戏规则
- **Lowy**: 最低安全高度，低于此值玩家将被送回重生点，默认 `0.0`。
- **LevelName**: 搭路世界名称，设为 `"bpractise"`。
- **SpawnPoint**: 玩家出生坐标，例如 `"4.5,24.0,10.5"`。

#### 命令配置
- **CommandName**: 主命令名称，如 `/qkbr`。
- **JoinCommand**: 加入命令参数，使用 `"join"`。
- **QuitCommand**: 退出命令参数，为 `"quit"`。

#### 命令权限
- **CommandWhiteList**: 允许的命令列表，例 `["hub"]` 表示允许使用 `/hub` 命令。

## 反馈与支持
如果有任何问题或发现bug，请加入我们的Q群反馈：894279534。
