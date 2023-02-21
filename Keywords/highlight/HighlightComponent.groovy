package highlight

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

public class HighlightComponent {
    @Keyword
    public static void on(TestObject testObject) {
        drawOutline(testObject)
    }

    private static void drawOutline(TestObject testObject) {
        try {
            WebDriver driver = DriverFactory.getWebDriver();
            List<WebElement> elements = WebUiCommonHelper.findWebElements(testObject, 20);
            for (WebElement element : elements) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style','background: #DFFF00; border: 1px solid black;');", element);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getHighlightableBuiltinKeywords() {
        List<MetaMethod> metaMethods = WebUiBuiltInKeywords.metaClass.getMethods();

        Set<String> highlightables = new HashSet<String>();
        for (MetaMethod method : metaMethods) {
            if (method.isStatic() && method.isPublic()) {
                Class<?>[] parameterTypes = method.nativeParameterTypes;
                if (parameterTypes.length > 0 && parameterTypes[0].isAssignableFrom(TestObject.class)) {
                    highlightables.add(method.getName());
                }
            }
        }
        return highlightables;
    }

    public static final Set<String> DEFAULT_HIGHLIGHTING_KW = Set.of(
        "click",
        "getText",
        "getElementWidth",
        "getElementHeight",
        "selectOptionByIndex",
        "selectOptionByLabel",
        "selectOptionByValue",
        "setEncryptedText",
        "setText"
    );

    private final Set<String> highlightingKW;
    HighlightComponent() {
        this.highlightingKW = new HashSet<>(DEFAULT_HIGHLIGHTING_KW);
    }

    @Keyword
    public void method(List<String> keywordsToAdd) {
        Set<String> highlightableKeywords = getHighlightableBuiltinKeywords();
        keywordsToAdd.stream()
            .filter(highlightableKeywords::contains)
            .forEach(this.highlightingKW::add);

        Set<String> influencedKeywords = this.getHighlightingKeywords();
        WebUiBuiltInKeywords.metaClass.static.invokeMethod = (name, args) -> {
            if (influencedKeywords.contains(name)) {
                TestObject to = (TestObject) args[0];
                HighlightComponent.on(to);
            }
            return delegate.metaClass.getMetaMethod(name,args).invoke(delegate, args);
        };
    }

    public void markKeywords(List<String> keywordsToAdd) {
        Objects.requireNonNull(keywordsToAdd);
        Set<String> highlightables = getHighlightableBuiltinKeywords();
        keywordsToAdd.stream()
            .filter(kw -> highlightables.contains(kw))
            .forEach(this.highlightingKW::add);
    }

    public Set<String> getHighlightingKeywords() {
        return new HashSet<>(highlightingKW);
    }
}
