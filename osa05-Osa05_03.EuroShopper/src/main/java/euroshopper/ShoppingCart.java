package euroshopper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {
    private Map<Item, Long> items = new HashMap<>();

    public Map<Item, Long> getItems() {
        return this.items;
    }

    public void addToCart(Item item) {
        items.put(item, items.getOrDefault(item, 0L) + 1);
    }

}
