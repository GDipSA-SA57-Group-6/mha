//package sg.nus.iss.mha;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import sg.nus.iss.mha.model.DailyExercise;
//import sg.nus.iss.mha.model.User;
//import sg.nus.iss.mha.repository.DailyExerciseRepository;
//import sg.nus.iss.mha.repository.UserRepository;
//
//@SpringBootApplication
//public class MhaApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(MhaApplication.class, args);
//	}
//
//	@Bean
//	CommandLineRunner loadData(UserRepository uRepo,
//								DailyExerciseRepository exRepo
//
//			) {
//    return (args) -> {
//
//    	User admin = new User("admin iss", "0adminpassword",LocalDate.of(2001,9,18),"male","admin@email.com");
//        User tin = new User("tin nus", "0tinpassword",LocalDate.of(1998,2,7),"male", "tin@email.com");
//        User lifang = new User("Li Fang", "0lfpassword",LocalDate.of(1998,2,7),"female", "lifang@email.com");
//
//        uRepo.save(admin);
//        uRepo.save(tin);
//        uRepo.save(lifang);
//
//        DateTimeFormatter df = DateTimeFormatter.ofPattern(User.DATE_FORMAT);
//        LocalDate day1 = LocalDate.parse("2024-01-01", df);
//        LocalDate day2 = LocalDate.parse("2024-01-02", df);
//        LocalDate day3 = LocalDate.parse("2024-01-03", df);
//        LocalDate day4 = LocalDate.parse("2024-01-04", df);
//        LocalDate day5 = LocalDate.parse("2024-01-05", df);
//        LocalDate day6 = LocalDate.parse("2024-01-06", df);
//        LocalDate day7 = LocalDate.parse("2024-01-07", df);
//        LocalDate day8 = LocalDate.parse("2024-01-08", df);
//        LocalDate day9 = LocalDate.parse("2024-01-09", df);
//
//        exRepo.save(new DailyExercise(day1, admin, 1100));
//        exRepo.save(new DailyExercise(day2, admin, 1200));
//        exRepo.save(new DailyExercise(day3, admin, 1300));
//        exRepo.save(new DailyExercise(day4, admin, 1400));
//        exRepo.save(new DailyExercise(day5, admin, 1500));
//        exRepo.save(new DailyExercise(day6, admin, 1600));
//        exRepo.save(new DailyExercise(day7, admin, 1700));
//        exRepo.save(new DailyExercise(day8, admin, 1800));
//        exRepo.save(new DailyExercise(day9, admin, 1900));
//
//        exRepo.save(new DailyExercise(day1, tin, 1100));
//
//      };
//	}
//}


package sg.nus.iss.mha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.mha.model.DailyExercise;
import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.repository.DailyExerciseRepository;
import sg.nus.iss.mha.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class MhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhaApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, DailyExerciseRepository dailyExerciseRepository) {
        return args -> {
            // 初始化用户
            User admin = new User("admin iss", "0adminpassword", LocalDate.of(2001, 9, 18), "male", "admin@email.com");
            User tin = new User("tin nus", "0tinpassword", LocalDate.of(1998, 2, 7), "male", "tin@email.com");
            User lifang = new User("Li Fang", "0lfpassword", LocalDate.of(1998, 2, 7), "female", "lifang@email.com");

            userRepository.save(admin);
            userRepository.save(tin);
            userRepository.save(lifang);

            // 初始化每日锻炼记录
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate[] days = {
                    LocalDate.parse("2024-01-01", df),
                    LocalDate.parse("2024-01-02", df),
                    LocalDate.parse("2024-01-03", df),
                    // ... (其他日期)
            };

            for (LocalDate day : days) {
                DailyExercise exercise = new DailyExercise(day, admin, 1100); // 假设卡路里消耗为1100
                dailyExerciseRepository.save(exercise);
            }

            // 为其他用户创建每日锻炼记录
            // dailyExerciseRepository.save(new DailyExercise(day1, tin, 1100));
            // ...
        };
    }
}