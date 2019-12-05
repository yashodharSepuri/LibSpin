# LibSpin
Easiest way to add Spin and Win on Android. LibSpin offers an Interactive Gamified Interface with lots of Customization options.

## Usage
Include SpinWheel in your layout :

```xml

<com.yashdev.libspin.SpinWheel
    android:id="@+id/spinWheel"
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:spnwTopTextColor="#263238"
    app:spnwBackgroundColor="#FF9800"
    app:spnwCursor="@drawable/ic_cursor"
    app:spnwCenterImage="@drawable/ic_center_image_1" />

```

And Initialize the SpinWheel :

```java

SpinWheel spinWheel = findViewById(R.id.spinWheel);
List<SpinItem> data = new ArrayList<>();

// Creating 5 Spin Items for demo purpose
for (int i=1; i <= 5; i++) {
    
    SpinItem spinItem = new SpinItem();
    spinItem.topText = i;
    spinItem.icon = R.drawable.ic_any_image;
    spinItem.color = 0xffFFF3E0;
    data.add(spinItem);
}

// Setting prviously created Data to SpinWheel
spinWheel.setData(data);
spinWheel.setRound(5);

// Start the Spin
int index = getRandomIndex();
spinWheel.startSpinWheelWithTargetIndex(index);

// Spin Wheel onFinishListener
spinWheel.setSpinWheelRoundItemSelectedListener(new SpinWheel.SpinWheelRoundItemSelectedListener() {
    @Override
    public void SpinWheelRoundItemSelected(int index) {
    
        // Reward here
        String topText = data.get(index).topText;
                                                       
        Toast.makeText(getApplicationContext(), data.get(index).topText, Toast.LENGTH_SHORT).show();
    }
});

```

## Example

For an example, please check the MainActivity class in the provided Sample App.

## License
```
MIT License

Copyright (c) 2019 yashDev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
