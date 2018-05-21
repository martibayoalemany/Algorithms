#!/usr/bin/env ruby
module LocalModule
    puts 'LocalModule loaded'
    s="LocalModule name"
    define_method(:getName) { puts s }
    define_method(:rem_norm) { puts (65665.to_f / 2) -  (65665.to_f / 2).round }
end

include LocalModule


class BinaryGap
    attr_accessor :name
    
    def initialize(name = "Binary gap algorithm")
        @name = name
    end

    def to_string()
        if @name.nil?
            puts @name
        end
    end

    def rem_norm(value) 
        return (value.to_f / 2 - (value.to_f / 2).floor).round
    end

    def execute(param = 654345)  
        value = param;
        puts "#{value}"
        while (rem_norm(value)  == 0)
            value = value / 2 
        end
                
        currentGap = 0;
        maxGap = 0;
        while value > 0 
            remainder = rem_norm(value)
            #puts "#{value}  - " + remainder.to_s
            if remainder == 0
                currentGap = currentGap + 1
             elsif currentGap != 0
                maxGap = maxGap < currentGap ? currentGap : maxGap
                currentGap = 0;
            end
            value = value / 2
        end
        return maxGap
    end
end


if __FILE__ == $0
        
    puts "_" * 60
    puts getName
    puts "-" * 60
    bg = BinaryGap.new()
    puts bg.respond_to?("execute")
    result = bg.rem_norm(654345)
    puts "#{result}" 
    puts bg.name
    result = bg.execute(654345) 
    puts result

end