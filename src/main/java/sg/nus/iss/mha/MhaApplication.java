package sg.nus.iss.mha;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.mha.model.DailyExercise;
import sg.nus.iss.mha.repository.DailyExerciseRepository;
import sg.nus.iss.mha.repository.UserRepository;

@SpringBootApplication
public class MhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhaApplication.class, args);
    }
    
    /*
	@Bean
	CommandLineRunner loadData(FoodRepository foodRepository) {
			return (args) -> {
				Food food1 = new Food();
				food1.setName("banana");
				food1.setQuantity_description("1 big");
				food1.setProtein(1);
				food1.setCal(105);
				food1.setCarb(27);
				food1.setFat(0);
				
				foodRepository.save(food1);
				
				
				Food food2 = new Food();
				food2.setName("pasta");
				food2.setQuantity_description("2 ounces");
				food2.setProtein(10);
				food2.setCal(200);
				food2.setCarb(40);
				food2.setFat(1);
				
				foodRepository.save(food2);
				
				Food food3 = new Food();
				food3.setName("coke");
				food3.setQuantity_description("200ml");
				food3.setProtein(0);
				food3.setCal(84);
				food3.setCarb(21);
				food3.setFat(0);
				
				foodRepository.save(food3);
				
				
				Food food4 = new Food();
				food4.setName("rice");
				food4.setQuantity_description("1/2 cup");
				food4.setProtein(2);
				food4.setCal(102);
				food4.setCarb(22);
				food4.setFat(0);
				
				foodRepository.save(food4);
				
				
				
				Food food5 = new Food();
				food5.setName("steak");
				food5.setQuantity_description(" 6 ounces");
				food5.setProtein(38);
				food5.setCal(384);
				food5.setCarb(0);
				food5.setFat(26);
				
				foodRepository.save(food5);
				
				
				Food food6 = new Food();
				food6.setName("seafood");
				food6.setQuantity_description("200g");
				food6.setProtein(20);
				food6.setCal(200);
				food6.setCarb(5);
				food6.setFat(10);

				
				foodRepository.save(food6);
				
				Food food7 = new Food();
				food7.setName("egg");
				food7.setQuantity_description("1 big");
				food7.setProtein(6);
				food7.setCal(70);
				food7.setCarb(1);
				food7.setFat(5);

				
				foodRepository.save(food7);
				
				Food food8 = new Food();
				food8.setName("cookie");
				food8.setQuantity_description("1 big");
				food8.setProtein(2);
				food8.setCal(200);
				food8.setCarb(25);
				food8.setFat(10);

				
				foodRepository.save(food8);
				
				Food food9 = new Food();
				food9.setName("orange_juice");
				food9.setQuantity_description("200 ml");
				food9.setProtein(1);
				food9.setCal(88);
				food9.setCarb(22);
				food9.setFat(1);
				
				foodRepository.save(food9);


				Food food10 = new Food();
				food10.setName("porridge");
				food10.setQuantity_description("240g");
				food10.setProtein(4);
				food10.setCal(150);
				food10.setCarb(40);
				food10.setFat(2);
				
				foodRepository.save(food10);


				Food food11 = new Food();
				food11.setName("hamburger");
				food11.setQuantity_description("240g");
				food11.setProtein(12);
				food11.setCal(250);
				food11.setCarb(20);
				food11.setFat(20);
				
				foodRepository.save(food11);


				Food food12 = new Food();
				food12.setName("salad");
				food12.setQuantity_description("100g");
				food12.setProtein(1);
				food12.setCal(10);
				food12.setCarb(5);
				food12.setFat(1);
				
				foodRepository.save(food12);
		};
		
	}
	*/

    
    /*
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, DailyExerciseRepository dailyExerciseRepository) {
        return args -> {
            // 初始化用户
            User zhimou = new User("fangzhimou131", "nevergivein131", LocalDate.of(1998, 2, 7), "male", "zhimou@email.com");
            User lifang = new User("Li Fang", "0lfpassword", LocalDate.of(1970, 2, 7), "female", "lifang@email.com");

            userRepository.save(zhimou);
            userRepository.save(lifang);
         
        };
    }
    */
    
    /*
	@Bean
	CommandLineRunner loadData(UserRepository uRepo, 
			DailyExerciseRepository dailyExerciseRepo) {
			return (args) -> {
				ZoneId zoneId = ZoneId.of("Asia/Singapore");
				DailyExercise exercise7 = new DailyExercise(LocalDate.now(zoneId), uRepo.findById(1).orElse(null), 0);
			    dailyExerciseRepo.save(exercise7);
			    DailyExercise exercise6 = new DailyExercise(LocalDate.now(zoneId).minusDays(1), uRepo.findById(1).orElse(null), 250);
			    dailyExerciseRepo.save(exercise6);
			    DailyExercise exercise5 = new DailyExercise(LocalDate.now(zoneId).minusDays(2), uRepo.findById(1).orElse(null), 150);
			    dailyExerciseRepo.save(exercise5);
			    DailyExercise exercise3 = new DailyExercise(LocalDate.now(zoneId).minusDays(4), uRepo.findById(1).orElse(null), 50);
			    dailyExerciseRepo.save(exercise3);
			    DailyExercise exercise2 = new DailyExercise(LocalDate.now(zoneId).minusDays(5), uRepo.findById(1).orElse(null), 100);
			    dailyExerciseRepo.save(exercise2);
			    DailyExercise exercise1 = new DailyExercise(LocalDate.now(zoneId).minusDays(6), uRepo.findById(1).orElse(null), 250);
			    dailyExerciseRepo.save(exercise1);
			};
	}		 
	*/
    
}