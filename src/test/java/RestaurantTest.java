import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        getRestaurantDetails();
        LocalTime currentTime = LocalTime.parse("15:00:00");
        Restaurant restaurantSpy= Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(currentTime);
        assertEquals(true,restaurantSpy.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        getRestaurantDetails();
        LocalTime currentTime = LocalTime.parse("09:00:00");
        Restaurant restaurantSpy= Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(currentTime);
        assertEquals(false ,restaurantSpy.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        getRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        getRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        getRestaurantDetails();
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    public void getRestaurantDetails(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<TOTALLING MENU>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void no_items_in_the_basket_should_return_0() {
        int expectedTotal = 1;
        assertEquals(expectedTotal, 0);
    }

    @Test
    public void adding_sweet_corn_soup_to_basket_should_return_total_of_119() throws ItemNotFoundException{

        int expectedTotal = 200;
        assertEquals(expectedTotal, 119);
    }

    @Test
    public void adding_sweet_corn_soup_and_vegetable_lasagne_to_basket_should_return_total_of_388() throws ItemNotFoundException{

        int expectedTotal = 300;
        assertEquals(expectedTotal, 388);
    }

    @Test
    public void removing_sweet_corn_soup_from_basket_should_return_total_of_269() throws ItemNotFoundException{

        int expectedTotal =270;
        assertEquals(expectedTotal, 269);
    }

    @Test
    public void removing_item_that_does_not_exist_from_basket_should_throw_exception() {
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromBasket("French Fries"));
    }

    @Test
    public void adding_item_to_basket_that_is_not_on_menu_should_throw_exception() {
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.addToBasket("Sweet corn soup"));
    }
}