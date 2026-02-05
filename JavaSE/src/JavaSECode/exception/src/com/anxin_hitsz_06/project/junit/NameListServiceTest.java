package com.anxin_hitsz_06.project.junit;

import com.anxin_hitsz_06.project.domain.Employee;
import com.anxin_hitsz_06.project.service.NameListService;
import org.junit.Test;

/**
 * ClassName: NameListServiceTest
 * Package: com.anxin_hitsz_06.project.junit
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 19:50
 * @Version 1.0
 */
public class NameListServiceTest {
    @Test
    public void testGetAllEmployees() {
        NameListService nameListService = new NameListService();

        Employee[] employees = nameListService.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee() {
        try {
            NameListService nameListService = new NameListService();
            int id = 3;
            id = 13;
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
