# waterbird-common
轻量级android工具类，辅助类，通用类

# log
    Log实现带开关的日志功能，同时允许格式化的日志输出和日志的累积输出；LogReader允许将日志存在在SD卡中中

### view
    自定义了一些常使用的view，例如TitleBar实现多功能标题栏，允许指定不同的功能的标题栏；

### assist
    Base64实现二进制数据与Base64格式之间的编解码；
    Check实现isEmpty的重载，用于判断对象，集合等常见数据结构是否为空；
    FlashLight实现手电筒的开关；
    KeyGuardLock对锁屏进行管理，WakeLock实现亮屏和灭屏，双方结合可以实现Android屏幕保护，实际是由这样的需求的，例如在一些营业厅、展厅中由于手机或平板是长时间供电不需要考虑电量问题，因此可以通过实现手机或平板屏幕常亮不能锁屏来实现Android屏幕保护。
    Network工具类，获取设备网络是否连接，采用的是wifi还是mobile方式进行连接，获取网络类型，打印网络状态信息，wifi状态或mobile状态是否可用等；
    Toastor Toast优化解决长时间连续单击弹出Toast信息时会出现延迟显示的问题，并对Toast进行单例封装避免多次创建Toast对象；
    SilentInstaller实现以root权限安装卸载APP，同时提供获取系统默认安装位置的功能，判断应用是系统应用还是用户应用；

### data
    DataKeeper对SharedPreference进行封装，实现数据的存储和读取；

### IO
    FilenameUtils 用于对文件名，路径进行处理；实现Windows和Linux下对文件名，路径名的统一处理；例如判断是否是换行符，对文件名、路径名进行规范化(//foo//./bar -->   /foo/bar)，获取文件路径，文件名，文件后缀等操作；
    FileUtil中封装了和文件相关的方法，主要有创建文件，复制文件，读取文件内容，写入文件内容，删除文件，复制文件夹，清空文件夹，，创建文件夹，获取文件大小文件夹大小，文件大小的格式化，移动文件，获取文件输入流、输出流，文件是否是同一个文件，将URL转发一个文件。
    IOUtil主要实现字符串与字节数组之间的转换；

### 广播接收者：
    PhoneReceiver电话接收者用于监听来电去电时的电话状态；
    ScreenReceiver用于接收在屏幕锁屏、解屏时的广播信息；
    SmsReceiver用于接收收到短信是的广播信息；
    TimeReceiver用于接收时区，时间改变时的广播；

### 服务：
    NotificationService服务用于实现APP的消息通知；

### utils
    AlarmUtil用于实现定时器的控制；
    AndroidUtil获取手机信息，MAC地址，开机时间，打印系统信息；
    AppUtil调用系统分享，判断是否前台运行，获取App包 信息版本号
    BitmapUtil实现Bitmap与字节流、Base64编码、Drawable的转换；同时允许对图片按比例进行缩放，计算图片的缩放比例，进行图片压缩等；
    ByteUtil实现对象和字节数组的转换；
    ClassUtil工具用于判断类是否是基础数据类型、数组、集合，获取类对应的对象实例；
    FieldUtil工具采用反射机制实现对对象的属性设置，获取属性类型信息等功能；
    ClipboardUtil实现字符串与Clipboard间复制、获取工作；
    CpuUtil获取手机CPU相关信息；
    DialogUtil实现Dialog的简单封装，用于简单信息对话框的显示；
    DisplayUtil获取显示器相关信息；
    HandlerUtil实现对Handler的简单封装；
    HexUtil实现向十六进制数据的转换；
    InputMethodUtils输入法工具列，用于显示隐藏输入键盘；
    MD5Util实现MD5加密；
    MemoryUtil获取内存信息，包括总内存，可用内存；
    NotificationUtil实现通知消息；
    NumberUtil实现String类型数字到int，long，float，double类型的转换；
    PackageUtil使用系统安装应用，卸载应用，打开应用详情，获取应用程序信息等；
    PollingUtil实现轮询服务；
    RandomUtil随机数工具类；
    SdCardUtil获取SD卡上的信息；
    TelephoneUtil获取电话的IMSI，IMIE，打印电话信息；
    VibrateUtil实现自定义模式的震动；
    ShellUtil执行shell指令；
    DensityUtil实现像素px到dp之间的相互转换；
